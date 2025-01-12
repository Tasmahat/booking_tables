import Header from "./Header";
import React, {useEffect, useState} from "react";
import DatePicker from "react-datepicker";
import {waitFor} from "@testing-library/react";

function UpdateBooking() {
    const [loading, setLoading] = useState(false);
    const [bookings, setBooking] = useState([]);
    const [tables, setTables] = useState([]);
    const [bookingDate, setBookingDate] = useState(null);
    const [bookingTime, setBookingTime] = useState(null);
    let fetchAdrBooking = 'api/api/booking/all';
    let fetchAdrTables = 'api/api/tables/all';
    let [loadingTables, setLoadingTables] = useState(true);

    useEffect(() => {
        setLoading(true);

        fetch(fetchAdrBooking)
            .then(response => response.json())
            .then(data => {
                setBooking(data);
                setLoading(false);
            });

        fetch(fetchAdrTables)
            .then(response => response.json())
            .then(data => {
                setTables(data);
            });
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    };


    function Update(event) {
        let id = event.target.id.substring(3)
        let name = document.getElementById("nameBookingUpdate" + id).value;
        let phone = document.getElementById("phoneBookingUpdate" + id).value;
        let comment = document.getElementById("commentBookingUpdate" + id).value;


        if(isNaN(+phone)) {
            alert("В телфоне должны быть только цифры!");
            return;
        }

        let table = document.getElementById("tableSelectUpdate" + id).value;

        console.log(table)

        const data = new FormData();
        data.append("id", id);
        if (name !== "") {
            data.append("name", name);
        } if (phone !== "") {
            data.append("phone", phone);
        } if (bookingDate != null) {
            data.append("date", bookingDate.toLocaleDateString());
        } if (bookingTime != null) {
            data.append("time", bookingTime.toLocaleTimeString());
        } if (comment !== "") {
            data.append("comment", comment);
        }
        data.append("tableId", table);

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                console.log(this.response);
                if(!this.response.includes("error")) {
                    alert("Вы изменили свою бронь!\nЖдите звонка для подтверждения!");
                    window.location.reload();
                } else {
                    alert("Что-то пошло не так!\nБронь не была изменена!")
                }
            }
        });

        xhr.open("POST", "api/api/booking/update?=");

        xhr.send(data);
    }

    let bookingList = [];
    let tableOptions = tables.map(table => {
        return (
            <option key={table.id} value={table.id}>{table.name}</option>
        )
    });

    bookings.map(async booking => {
        bookingList.push(
            <div key={booking.id}>
                <div><b>Имя забронировавшего столик:</b></div>
                <input type="text" id={"nameBookingUpdate" + booking.id} placeholder={booking.customerName}/>
                <div><b>Номер телефона:</b></div>
                <input type="text" id={"phoneBookingUpdate" + booking.id} placeholder={booking.phoneNumber}/>
                <div><b>Забронированный столик:</b></div>
                <select id={"tableSelectUpdate" + booking.id} defaultValue={booking.table.id}>
                    {tableOptions}
                </select>
                <div><b>Дата брони:</b></div>
                <DatePicker
                    selected={bookingDate}
                    onChange={(date) => setBookingDate(date)}
                    placeholderText={booking.date}
                    dateFormat="dd.MM.yyyy"/>
                <div><b>Время брони:</b></div>
                <DatePicker
                    selected={bookingTime}
                    onChange={(date) => setBookingTime(date)}
                    placeholderText={booking.time}
                    showTimeSelect
                    showTimeSelectOnly
                    timeIntervals={60}
                    timeCaption="Time"
                    dateFormat="HH:mm"/>
                <div><b>Коментарий к заказу:</b></div>
                <input type="text" id={"commentBookingUpdate" + booking.id} placeholder={booking.comment} size={100}/>
                <button onClick={Update} className={"update_button"} id={"upd" + booking.id}>Обновить</button>
                <div><br/><br/><br/></div>
            </div>
        )
        return 0;
    })

    return(
        <div>
            <Header/>
            <body className={"body"}>
                {bookingList}
            </body>
        </div>
    )
}

export default UpdateBooking;

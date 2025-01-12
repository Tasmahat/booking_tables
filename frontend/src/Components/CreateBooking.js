import React, {useEffect, useState} from "react";
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css'
import Header from "./Header";

function CreateBooking() {
    const [loading, setLoading] = useState(false);
    const [tables, setTables] = useState([]);
    const [bookingDate, setBookingDate] = useState(null);
    const [bookingTime, setBookingTime] = useState(null);
    let fetchAdr = 'api/api/tables/all';

    useEffect(() => {
        setLoading(true);

        fetch(fetchAdr)
            .then(response => response.json())
            .then(data => {
                setTables(data);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    };

    let tableOptions= tables.map(table => {
        return(
            <option key={table.id} value={table.id}>{table.name}</option>
        )
    });

    let name, phone, comment;

    function PostRequest() {
        name = document.getElementById("name").value;
        phone = document.getElementById("phone").value;
        comment = document.getElementById("comment").value;

        if(name === "") {
            alert("Напишите ваше имя!");
            return;
        }

        if(phone === "") {
            alert("Напишите ваш телефон!");
            return;
        } else if(isNaN(+phone)) {
            alert("В телфоне должны быть только цифры!");
            return;
        }

        if (bookingDate == null) {
           alert("Укажите дату, на которую бронируете столик!");
           return;
        }

        if (bookingTime == null) {
            alert("Укажите время, на которое бронируете столик!");
            return;
        }

        let table = document.getElementById("table0").value;

        const data = new FormData();
        data.append("name", name);
        data.append("phone", phone);
        data.append("tableId", table);
        data.append("date", bookingDate.toLocaleDateString());
        data.append("time", bookingTime.toLocaleTimeString());
        data.append("comment", comment);

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                console.log(this.response);
                if(!this.response.includes("error")) {
                    alert("Вы забронировали столик!\nЖдите звонка для подтверждения!");
                } else {
                    alert("Что-то пошло не так!\nСтолик не был забронирован!")
                }
            }
        });

        xhr.open("POST", "api/api/booking/create?=");

        xhr.send(data);
    }

    return(
        <>
            <Header/>
            <body className={"body"}>
                <h2>Забронируйте свой столик:</h2>
                <div>
                    <h2>Введите имя, на которое бронируете столик:</h2>
                    <input type="text" id="name" placeholder={"Например: Иван"}/>
                    <h2>Введите номер телефона, на который вам стоит позвонить:</h2>
                    <input type="text" id="phone" placeholder={"Например: 88005553535"}/>
                    <form id={"create-booking"}>
                        <select id={"table0"}>
                            {tableOptions}
                        </select>
                    </form>
                    <h2>Выберите дату брони:</h2>
                    <DatePicker
                        selected={bookingDate}
                        onChange={(date) => setBookingDate(date)}
                        placeholderText="Нажмите, чтобы выбрать дату"
                        dateFormat="dd.MM.yyyy"/>
                    <h2>Выберите время брони:</h2>
                    <DatePicker
                        selected={bookingTime}
                        onChange={(date) => setBookingTime(date)}
                        placeholderText="Нажмите, чтобы выбрать время"
                        showTimeSelect
                        showTimeSelectOnly
                        timeIntervals={60}
                        timeCaption="Time"
                        dateFormat="HH:mm"/>
                    <h2>Напишите комментарий к заказу:</h2>
                    <input type="text" id="comment" placeholder={"Например блюда, которые хотите заказать"} size={100}/>
                    <button onClick={PostRequest}>Добавить бронь</button>
                </div>
            </body>
        </>
    );
}

export default CreateBooking;
import Header from "./Header";
import React, {useEffect, useState} from "react";

function DeleteBooking() {
    const [loading, setLoading] = useState(false);
    const [bookings, setBooking] = useState([]);
    let fetchAdr = 'api/api/booking/all';

    useEffect(() => {
        setLoading(true);

        fetch(fetchAdr)
            .then(response => response.json())
            .then(data => {
                setBooking(data);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    };

    function Delete(event) {
        const data = new FormData();
        data.append("id", event.target.id.substring(3));

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                console.log(this.response);
                if(!this.response.includes("error")) {
                    alert("Вы удалили бронь!");
                    window.location.reload();
                } else {
                    alert("Что-то пошло не так!\nБронь не была удалена!")
                }
            }
        });

        xhr.open("DELETE", "api/api/booking/delete?=");

        xhr.send(data);
    }

    let bookingList = []

    bookings.map(booking => {
        bookingList.push(
            <div key={booking.id}>
                <div><b>Имя забронировавшего столик:</b></div>
                <div>{booking.customerName}</div>
                <div><b>Название забронированного столика:</b></div>
                <div>{booking.table.name}</div>
                <div><b>Дата брони:</b></div>
                <div>{booking.date}</div>
                <div><b>Время брони:</b></div>
                <div>{booking.time}</div>
                <button onClick={Delete} className={"delete_button"} id={"del" + booking.id}>Х</button>
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

export default DeleteBooking;

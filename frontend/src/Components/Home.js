import Header from "./Header";
import React, {useEffect, useState} from "react";

function Home() {
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

    let bookingList = []

    bookings.map(booking => {
        console.log(booking.customerName)
        bookingList.push(
            <div key={booking.id}>
                <div><b>Имя забронировавшего столик:</b></div>
                <div>{booking.customerName}</div>
                <div><b>Телефон забронировавшего столик:</b></div>
                <div>{booking.phoneNumber}</div>
                <div><b>Название забронированного столика:</b></div>
                <div>{booking.table.name}</div>
                <div><b>Дата брони:</b></div>
                <div>{booking.date}</div>
                <div><b>Время брони:</b></div>
                <div>{booking.time}</div>
                <div><b>Комментарий:</b></div>
                <div>{booking.comment}</div>
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

export default Home;

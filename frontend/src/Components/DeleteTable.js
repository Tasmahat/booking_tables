import Header from "./Header";
import React, {useEffect, useState} from "react";

function DeleteTable() {
    const [loading, setLoading] = useState(false);
    const [tables, setTables] = useState([]);
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

    function Delete(event) {
        const data = new FormData();
        data.append("id", event.target.id.substring(4));

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                console.log(this.response);
                if(!this.response.includes("error")) {
                    alert("Вы удалили столик!");
                    window.location.reload();
                } else {
                    alert("Что-то пошло не так!\nСтолик не был удален!")
                }
            }
        });

        xhr.open("DELETE", "api/api/tables/delete?=");

        xhr.send(data);
    }

    let tablesList = []

    tables.map(table => {
        tablesList.push(
            <div key={table.id}>
                <div><b>Имя столика:</b></div>
                <div>{table.name}</div>
                <button onClick={Delete} className={"delete_button"} id={"delT" + table.id}>Х</button>
                <div><br/><br/><br/></div>
            </div>
        )
        return 0;
    })

    return(
        <div>
            <Header/>
            <body className={"body"}>
            {tablesList}
            </body>
        </div>
    )
}

export default DeleteTable;

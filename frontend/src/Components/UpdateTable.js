import Header from "./Header";
import React, {useEffect, useState} from "react";
import DatePicker from "react-datepicker";

function UpdateTable() {
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

    function Update(event) {
        let id = event.target.id.substring(4)
        let name = document.getElementById("nameTableUpdate" + id).value;

        if (name === "") {
            alert("Кроме имени нечего менять!\nНапишите имя!");
            return;
        }

        const data = new FormData();
        data.append("id", id);
        data.append("name", name);

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                console.log(this.response);
                if(!this.response.includes("error")) {
                    alert("Вы изменили столик!");
                    window.location.reload();
                } else {
                    alert("Что-то пошло не так!\nСтолик не был изменен!")
                }
            }
        });

        xhr.open("POST", "api/api/tables/update?=");

        xhr.send(data);
    }

    let tablesList = [];

    tables.map(table => {
        tablesList.push(
            <div key={table.id}>
                <div><b>Имя забронированного столика:</b></div>
                <input type="text" id={"nameTableUpdate" + table.id} placeholder={table.name}/>
                <button onClick={Update} className={"update_button"} id={"updT" + table.id}>Обновить</button>
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

export default UpdateTable;

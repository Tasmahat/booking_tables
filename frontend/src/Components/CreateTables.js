import Header from "./Header";
import React, {useEffect, useState} from "react";

function CreateTables() {

    function PostRequest() {
        let name = document.getElementById("nameTable").value;

        if(name === "") {
            alert("Напишите ваше имя!");
            return;
        }

        const data = new FormData();
        data.append("name", name);

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                console.log(this.response);
                if(!this.response.includes("error")) {
                    alert("Вы создали столик!");
                } else {
                    alert("Что-то пошло не так!\nСтолик не был создан!")
                }
            }
        });

        xhr.open("POST", "api/api/tables/create?=");

        xhr.send(data);
    }

    return(
        <div>
            <Header/>
            <body className={"body"}>
                <h1>Создайте столик:</h1>
                <h2>Напишите название столика:</h2>
                <input type="text" id="nameTable" placeholder={"Напишите удобное для вас обозначение"}/>
                <button onClick={PostRequest}>Добавить столик</button>
            </body>
        </div>
    )
}

export default CreateTables;

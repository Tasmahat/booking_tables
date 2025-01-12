import React from "react";

function Header() {
    return (
        <header className="App-header">
            <Menu/>
        </header>
    );
}

function Menu() {
    return (
        <div className={"main-menu"}>
            <a href={"/"}>
                <button>Все брони</button>
            </a>
            <a href={"booking_create"}>
                <button>Забронировать</button>
            </a>
            <a href={"booking_delete"}>
                <button>Отменить бронь</button>
            </a>
            <a href={"booking_update"}>
                <button>Изменить бронь</button>
            </a>
            <a href={"tables_create"}>
                <button>Создать столик</button>
            </a>
            <a href={"tables_update"}>
                <button>Изменить столик</button>
            </a>
            <a href={"tables_delete"}>
                <button>Убрать столик</button>
            </a>
        </div>
    )
}
export default Header;
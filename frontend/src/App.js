import {BrowserRouter as Router, Routes, Route} from "react-router-dom";

import './App.css'
import CreateBooking from "./Components/CreateBooking";
import Home from "./Components/Home";
import DeleteBooking from "./Components/DeleteBooking";
import CreateTables from "./Components/CreateTables";
import DeleteTable from "./Components/DeleteTable";
import UpdateBooking from "./Components/UpdateBooking";
import UpdateTable from "./Components/UpdateTable";


function App() {

    return(
        <Router>
            <Routes>
                <Route path={'/'} element={<Home/>}/>
                <Route path={"/booking_create"} element={<CreateBooking/>}/>
                <Route path={"/booking_delete"} element={<DeleteBooking/>}/>
                <Route path={"/booking_update"} element={<UpdateBooking/>}/>
                <Route path={"/tables_create"} element={<CreateTables/>}/>
                <Route path={"/tables_delete"} element={<DeleteTable/>}/>
                <Route path={"/tables_update"} element={<UpdateTable/>}/>
            </Routes>
        </Router>
    )
}

export default App;

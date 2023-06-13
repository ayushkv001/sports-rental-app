import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const Order = () => {
    const [name,setName] = useState("");
    const [email,setEmail] = useState("");
    const [phone,setPhone] = useState("");
    const [duration,setDuration] = useState("");

    const naviagte = useNavigate();

    const {id,cost} = useParams();

    const onClick = (event) => {
        event.preventDefault();
        const obj = {
            id:`${id}`,
            renter_name:name,
            email:email,
            phone:phone,
            duration:`${duration} days`,
            cost: `${duration*cost}`
        }

        console.log(obj)

        axios.post('http://localhost:8080/getData/order',obj);

        naviagte('/');
    }


    return <div className="att3">
        <form onSubmit={onClick}>
            <label>Enter your name: {" "}
                <input
                    type="text" 
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
            </label>
            <br/>
            <label>Enter your email:{" "}
                <input
                    type="text" 
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </label>
            <br/>
            <label>Enter your phone: {" "}
                <input
                    type="text" 
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                />
            </label>
            <br/>
            <label>Enter duration in days:{" "}
                <input
                    type="text" 
                    value={duration}
                    onChange={(e) => setDuration(e.target.value)}
                />
            </label>
            <br/>
            <button className="btn">Submit</button>
        </form>
    </div>
}

export default Order;
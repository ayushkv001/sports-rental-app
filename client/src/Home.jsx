import React, {  useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const Home = () => {
    const [data,setData] = useState([]);

    const getData = (type) => {
        axios.get(`http://localhost:8080/getData/${type}`).then(res => {
            console.log(res.data);
             let a = Array()
             let l = Object.keys(res.data).length;
             for(var i=0;i<l;i++){
                const sep = {
                    id:res.data[i][3],
                    name: res.data[i][0],
                    cost:res.data[i][1],
                    image:res.data[i][2]
                }
                a.push(sep)
             }

             setData(a)
             console.log(a)
        })
    }



    return <>
        <div className="att">
            <button onClick={()=>getData('cycle')}>Cycle</button>
            <button onClick={()=>getData('tennis')}>Tennis</button>
            <button onClick={()=>getData('badminton')}>Badminton</button>
        </div>
        {
            data.map(({id,name,cost,image},key) => {
                const srcString = `http://localhost:8080/getData/serve-image/${image}`;
                return <div key={key} className="att2">
                    <div>Name : {name}</div>
                    <div>Cost : Rs. {cost}/day</div>
                    <div>Image : <img src={srcString} alt="image not available" length="150px" width="150px"/></div>
                    <div><Link to={{
                        pathname:`/order/${id}/${cost}`,
                    }}
                    ><button className="btn">Rent</button></Link></div>
                </div>
            })
        }
    </>;
}

export default Home;
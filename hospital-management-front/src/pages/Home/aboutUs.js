import React from "react";
import Card from "../../components/branchCard/branchCard";
import { data } from "./helper";
import "./home.css";

const AboutUs = () => {
  return (
   <div>
    <h1>About.</h1>
     <div className="card-container">
      {data.map((item) => (
        <Card data={item} />
      ))}
    </div>
   </div>
  );
};

export default AboutUs;

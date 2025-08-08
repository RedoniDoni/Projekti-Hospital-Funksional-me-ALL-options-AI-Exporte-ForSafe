import React from "react";
import "./branchCard.css";

const Card = (data) => {
  const { title, content, image } = data.data;
  return (
    <div className="card">
      <div className="card-image">{image}</div>
      <div className="card-content">
        <h3>{title}</h3>
        <p>{content}</p>
      </div>
    </div>
  );
};
export default Card;

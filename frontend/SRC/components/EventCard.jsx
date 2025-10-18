import React from "react";
import "../styles/components.css";

function EventCard({ title, date, status }) {
  return (
    <div className="event-card">
      <h3>{title}</h3>
      <p>{date}</p>
      <span className="event-status">{status}</span>
    </div>
  );
}

export default EventCard;

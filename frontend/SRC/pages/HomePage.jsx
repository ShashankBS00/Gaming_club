import React from "react";
import EventCard from "../components/EventCard";
import "../styles/home.css";

function HomePage() {
  return (
    <div>
      {/* Hero Section */}
      <div className="hero">
        <div className="hero-content">
          <h1>Welcome to the Gaming Club</h1>
          <p>Compete. Connect. Conquer.</p>
          <button className="btn-neon">Join the Battle</button>
        </div>
      </div>

      {/* Event Cards Section */}
      <div className="event-section">
        <h2>Upcoming Events</h2>
        <div className="event-grid">
          <EventCard title="Valorant Cup" date="March 25, 2025" status="Open" />
          <EventCard title="FIFA Tournament" date="April 5, 2025" status="Upcoming" />
          <EventCard title="CS:GO Clan War" date="April 12, 2025" status="Full" />
        </div>
      </div>
    </div>
  );
}

export default HomePage;

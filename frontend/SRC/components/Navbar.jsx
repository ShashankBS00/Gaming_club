import React from "react";

function Navbar() {
  return (
    <nav style={{ display: 'flex', justifyContent: 'space-between', padding: '20px', background: '#1a1a24' }}>
      <div style={{ color: '#ff007f', fontWeight: 'bold' }}>Gaming Club</div>
      <div>
        <a href="/" style={{ color: 'white', marginLeft: '20px' }}>Home</a>
        <a href="/events" style={{ color: 'white', marginLeft: '20px' }}>Events</a>
        <a href="/login" style={{ color: 'white', marginLeft: '20px' }}>Login</a>
      </div>
    </nav>
  );
}

export default Navbar;

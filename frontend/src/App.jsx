import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Layout from "./layouts/ClientLayout";
import Home from "./pages/Home";
import Community from "./pages/Community";
import Profile from "./pages/Profile";
import Featured from "./pages/Featured";
import Following from "./pages/Following";
import Learn from "./pages/Learn";
import Academy from "./pages/Academy";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route element={<Layout />}>
          <Route path="/" element={<Home />} />
          <Route path="/community" element={<Community />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/featured" element={<Featured />} />
          <Route path="/following" element={<Following />} />
          <Route path="/learn" element={<Learn />} />
          <Route path="/academy" element={<Academy />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;

import React, { useState } from "react";
import cover from "../assets/pics/im4.jpeg"; // Replace with your actual cover image path
import avatar from "../assets/pics/im4.jpeg";
import MyPosts from "../components/profile/MyPosts"; 
import MyCourses from "../components/profile/MyCourses";
import MyCertifications from "../components/profile/MyCertifications"; 

const tabs = ["My Posts", "My Courses", "My Certifications"];

const Profile = () => {
  const [activeTab, setActiveTab] = useState("My Posts");

  const postData = [
  {
    id: 1,
    src: "/assets/pics/tiger.jpg",
    username: "johndoe",
    likes: 120,
    comments: 15,
    caption: "Wild moments in the jungle",
  },
  {
    id: 2,
    src: "/assets/pics/river.jpg",
    username: "naturegal",
    likes: 89,
    comments: 8,
    caption: "Peaceful streams 🏞️",
  },
  // ...more posts
];

  

  return (
    <div className="min-h-screen bg-black text-white font-inter">
      {/* Cover */}
      <div className="relative">
        <img
          src={cover}
          alt="cover"
          className="w-full h-56 object-cover rounded-b-lg"
        />

        {/* Profile Section (stacked in mobile, row in desktop) */}
        <div className="px-4 md:px-8 -mt-10 flex flex-col md:flex-row items-start md:items-center justify-between">
          {/* Avatar + Info */}
          <div className="flex flex-col md:flex-row items-start md:items-center gap-6">
            <img
              src={avatar}
              alt="avatar"
              className="w-48 h-48 rounded-full border-4 border-black object-cover  -mt-8"
            />
            <div className="mt-4 md:mt-8">
              <h1 className="text-2xl font-bold">John Doe</h1>
              <div className="flex gap-10 mt-2">
                <div className="text-center">
                  <p className="text-sm text-gray-400">Posts</p>
                  <p className="font-semibold text-lg">56</p>
                </div>
                <div className="text-center">
                  <p className="text-sm text-gray-400">Followers</p>
                  <p className="font-semibold text-lg">124</p>
                </div>
                <div className="text-center">
                  <p className="text-sm text-gray-400">Following</p>
                  <p className="font-semibold text-lg">26</p>
                </div>
              </div>
            </div>
          </div>

          {/* Edit Button */}
          <div className="mt-4 md:mt-0">
            <button className="bg-transparent border border-white rounded-full px-4 py-1 hover:bg-white hover:text-black transition">
              Edit Profile
            </button>
          </div>
        </div>
      </div>

      {/* User Info */}
      <div className=" mt-0 md:px-8 px-4">
        <div className=" md:ml-5 ml-0 flex flex-wrap justify-start items-start">
          <div className="text-start">
            <p className="mt-4 text-sm text-gray-300">
              Photography is life.... <br />
              Nikon Gear User. <br />
              AKA John
            </p>
          </div>
        </div>

        {/* Tabs */}
        <div className="flex space-x-6 mt-6 border-b border-gray-600 ">
          {tabs.map((tab) => (
            <button
              key={tab}
              className={`text-md font-medium ${
                activeTab === tab
                  ? "text-BabyBlue border-b-2 border-BabyBlue pb-3"
                  : "text-gray-400 pb-3"
              }`}
              onClick={() => setActiveTab(tab)}
            >
              {tab}
            </button>
          ))}
        </div>

        {/* Tab Content: Grid */}
        {activeTab === "My Posts" && (
          <div className="w-full">
              <MyPosts posts={postData} />

          </div>
        )}

        {/* Placeholder tabs */}
        {activeTab === "My Courses" && (
          <div className="w-full">
            <MyCourses />
            </div>
        )}
        {activeTab === "My Certifications" && (
          <div className="w-full">
           <MyCertifications />
          </div>
        )}
      </div>
    </div>
  );
};

export default Profile;

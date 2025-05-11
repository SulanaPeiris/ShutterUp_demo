import axios from "axios";

const API = "http://localhost:8080/api/tutorial-progress";
const token = sessionStorage.getItem("token");

const config = {
  headers: {
    Authorization: `Bearer ${token}`,
  },
};

export const fetchEnrolledTutorials = async (userId) => {
  const res = await axios.get(`${API}/enrolled/${userId}`, config);
  return res.data;
};

export const generateCertificate = async (tutorialId, userId) => {
  const res = await axios.post(`${API}/generate-certificate`, null, {
    params: { tutorialId, userId },
    ...config,
  });
  return res.data;
};

export const fetchCompletedTutorials = async (userId) => {
  const res = await axios.get(`${API}/completed/${userId}`, config);
  return res.data;
};

export const enrollInTutorial = async (userId, tutorialId) => {
  const token = sessionStorage.getItem("token");
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  try {
    const res = await axios.post(
      `http://localhost:8080/api/tutorial-progress/enroll`,
      null,
      {
        params: { userId, tutorialId },
        ...config,
      }
    );
    return res.data;
  } catch (err) {
    console.error("Failed to enroll:", err);
    throw err;
  }
};

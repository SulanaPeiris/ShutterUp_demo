import axios from "axios";

const API = "http://localhost:8080/api/tutorials";
const token = sessionStorage.getItem("token");

const config = {
  headers: {
    Authorization: `Bearer ${token}`,
  },
};



export const createTutorial = async (payload) => {
  return await axios.post(`${API}/create`, payload, config);
};

export const updateTutorial = async (id, payload) => {
  return await axios.put(`${API}/${id}`, payload, config);
};

export const deleteTutorial = async (id) => {
  return await axios.delete(`${API}/${id}`, config);
};

export const fetchTutorialById = async (id) => {
  const res = await axios.get(`${API}/${id}`, config);
  return res.data;
};


export const fetchAllTutorials = async () => {
  const token = sessionStorage.getItem("token");
  const res = await axios.get("http://localhost:8080/api/tutorials", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.data;
};

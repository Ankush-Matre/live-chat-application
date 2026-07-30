import api from "./api";

export const getAllMessages = async () => {

    const response = await api.get("/api/messages/history");

    return response.data;
};
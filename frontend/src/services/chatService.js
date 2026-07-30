import api from "./api";

export const getChatHistory = async () => {

    const response = await api.get("/api/messages/history");

    return response.data;
};
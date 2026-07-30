import api from "./api";

export const getOnlineUsers = async () => {

    const response = await api.get("/api/online-users");

    return response.data;
};
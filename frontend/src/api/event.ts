// frontend/src/api/event.ts
import { apiClient } from "./instance";
import { EventResponse } from "../types/events";

export const fetchAllEvents = async (type?: string | null) => {
  const params = type ? { type } : {};
  const { data } = await apiClient.get<EventResponse[]>("/events/filter", {
    params,
  });
  return Array.isArray(data) ? data : [];
};

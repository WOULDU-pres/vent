// frontend/src/api/event.ts
import axios from 'axios';

export interface EventResponse {
  id: number;
  type: string;
  startTime: string;
  endTime: string;
  participantCount: number; 
}

export const fetchAllEvents = async (): Promise<EventResponse[]> => {
  // const { data } = await axios.get('http://localhost:8080/api/events');
  const { data } = await axios.get('/api/events');
  return Array.isArray(data) ? data : []; // 배열 보장
};
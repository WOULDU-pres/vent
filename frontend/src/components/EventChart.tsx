// frontend/src/components/EventChart.tsx
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, BarElement } from 'chart.js';
import { EventResponse } from '../api/event';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, BarElement);

interface Props {
  events: EventResponse[];
}

export default function EventChart({ events }: Props) {
    const data = {
        labels: events.map(e => e.type),
        datasets: [{
          label: '참여자 수',
          data: events.map(e => e.participantCount),
          backgroundColor: 'rgba(75, 192, 192, 0.6)'
        }]
      };
    
      return <Bar data={data} />;
}

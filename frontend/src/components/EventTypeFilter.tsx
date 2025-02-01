// src/components/EventTypeFilter.tsx
import { useEventFilterStore } from '../store/filterStore';

const EVENT_TYPES = [
  { value: '', label: '전체' },
  { value: 'flash-deal', label: '100원딜' },
  { value: 'raffle', label: '래플' },
  { value: 'attendance', label: '출석체크' },
];

export default function EventTypeFilter() {
  const { selectedType, setSelectedType } = useEventFilterStore();

  return (
    <div className="mb-6">
      <select
        value={selectedType || ''}
        onChange={(e) => setSelectedType(e.target.value || null)}
        className="px-4 py-2 border rounded-lg bg-white shadow-sm"
      >
        {EVENT_TYPES.map((type) => (
          <option key={type.value} value={type.value}>
            {type.label}
          </option>
        ))}
      </select>
    </div>
  );
}

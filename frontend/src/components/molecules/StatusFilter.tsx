// src/components/molecules/StatusFilter.tsx
import { cn } from "../../utils/cn";

const STATUS_OPTIONS = [
  { value: "ACTIVE", label: "진행중" },
  { value: "UPCOMING", label: "예정" },
  { value: "ENDED", label: "종료" },
];

interface Props {
  selectedStatus: string | null;
  onStatusChange: (status: string | null) => void;
}

export const StatusFilter = ({
  selectedStatus,
  onStatusChange,
}: Props): JSX.Element => {
  return (
    <div className="bg-surface-100 flex gap-2 rounded-lg p-1">
      <button
        onClick={() => onStatusChange(null)}
        className={cn(
          "rounded-md px-4 py-2 transition-colors",
          !selectedStatus
            ? "bg-primary text-surface-0"
            : "text-surface-600 hover:bg-surface-200",
        )}
      >
        전체
      </button>
      {STATUS_OPTIONS.map((option) => (
        <button
          key={option.value}
          onClick={() => onStatusChange(option.value)}
          className={cn(
            "rounded-md px-4 py-2 transition-colors",
            selectedStatus === option.value
              ? "bg-primary text-surface-0"
              : "text-surface-600 hover:bg-surface-200",
          )}
        >
          {option.label}
        </button>
      ))}
    </div>
  );
};

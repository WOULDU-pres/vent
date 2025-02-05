// frontend/tailwind.config.js
module.exports = {
  content: [
    './src/**/*.{js,ts,jsx,tsx}'
  ],
  theme: {
    extend: {
      colors: {
        primary: '#3B82F6',
        surface: {
          0: '#FFFFFF',
          100: '#F8FAFC',
          200: '#F1F5F9',
          600: '#4B5563',
          800: '#1F2937',
          900: '#111827'
        },
        status: {
          active: '#22C55E',
          upcoming: '#F59E0B',
          ended: '#6B7280'
        }
      },
      spacing: {
        xs: '4px',
        sm: '8px',
        md: '16px',
        lg: '24px'
      }
    },
    container: {
      center: true,
      padding: '1rem',
      screens: { xl: '1280px' }
    }
  }
}

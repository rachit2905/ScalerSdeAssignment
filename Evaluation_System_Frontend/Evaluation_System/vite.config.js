import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vitejs.dev/config/
export default defineConfig(
  {
    server: {
      proxy: {
        '/api': 'http://localhost:8080',
      }
    },
    plugins: [react()], optimizeDeps: {
      include: ['styled-components'],
    },
  })

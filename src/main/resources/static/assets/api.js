// small, robust API helper module used by student.js
export const API_BASE = '';
export const ENDPOINTS = {
  courses: '/api/courses',
  enroll: '/api/enroll',
  myEnrollments: '/api/me/enrollments',
  progress: '/api/me/progress'
};

// very small auth shim (compatible with earlier code)
export const auth = {
  token: localStorage.getItem('lms_token') || null,
  user: JSON.parse(localStorage.getItem('lms_user') || 'null'),
  clear(){ localStorage.removeItem('lms_token'); localStorage.removeItem('lms_user'); this.token = null; this.user = null; }
};

// basic toast used by UI (exported)
export function toast(msg){
  // simple fallback toast (UI created in student.html)
  const el = document.getElementById('toast');
  if(!el) {
    console.log('toast:', msg);
    return;
  }
  el.textContent = msg;
  el.style.display = 'block';
  clearTimeout(window.__toastTimer);
  window.__toastTimer = setTimeout(()=> el.style.display = 'none', 2200);
}

// small HTTP shim; if project provides its own http this can be replaced
export async function http(method, url, body){
  const headers = { 'Accept': 'application/json' };
  if (auth.token) headers['Authorization'] = `Bearer ${auth.token}`;
  if(method === 'GET' || method === 'DELETE'){
    const res = await fetch(url, { method, headers });
    if(!res.ok) throw new Error(await res.text());
    return res.json ? res.json() : null;
  } else {
    headers['Content-Type'] = 'application/json';
    const res = await fetch(url, { method, headers, body: JSON.stringify(body || {}) });
    if(!res.ok) throw new Error(await res.text());
    return res.json ? res.json() : null;
  }
}

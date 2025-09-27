document.addEventListener('DOMContentLoaded', () => {
  const contentDiv = document.getElementById('content');
  const navHome = document.getElementById('home');
  const navUsers = document.getElementById('users');
  const navCarreras = document.getElementById('carreras');
  const navPostulantes = document.getElementById('postulantes');

  const fetchAndRender = async (url, title, renderer) => {
    try {
      const response = await fetch(url);
      const data = await response.json();
      contentDiv.innerHTML = `<h2>${title}</h2>` + renderer(data);
    } catch (error) {
      console.error(`Error fetching ${title.toLowerCase()}:`, error);
      contentDiv.innerHTML = `<p>Error loading ${title.toLowerCase()}. Please try again later.</p>`;
    }
  };

  const renderUsers = (users) => {
    if (users.length === 0) return '<p>No users found.</p>';
    let list = '<ul>';
    users.forEach(user => {
      list += `<li>${user.fullName} (${user.username}) - ${user.Role ? user.Role.name : 'No role'}</li>`;
    });
    list += '</ul>';
    return list;
  };

  const renderCarreras = (carreras) => {
    if (carreras.length === 0) return '<p>No carreras found.</p>';
    let list = '<ul>';
    carreras.forEach(carrera => {
      list += `<li>${carrera.nombre}</li>`;
    });
    list += '</ul>';
    return list;
  };

  const renderPostulantes = (postulantes) => {
    if (postulantes.length === 0) return '<p>No postulantes found.</p>';
    let list = '<ul>';
    postulantes.forEach(postulante => {
      list += `<li>${postulante.nombres} ${postulante.apellidos} (${postulante.cedula})</li>`;
    });
    list += '</ul>';
    return list;
  };

  const showHome = () => {
    contentDiv.innerHTML = '<h2>Home</h2><p>Welcome to the admission system.</p>';
  };

  navHome.addEventListener('click', (e) => {
    e.preventDefault();
    showHome();
  });

  navUsers.addEventListener('click', (e) => {
    e.preventDefault();
    fetchAndRender('/api/users', 'Users', renderUsers);
  });

  navCarreras.addEventListener('click', (e) => {
    e.preventDefault();
    fetchAndRender('/api/carreras', 'Carreras', renderCarreras);
  });

  navPostulantes.addEventListener('click', (e) => {
    e.preventDefault();
    fetchAndRender('/api/postulantes', 'Postulantes', renderPostulantes);
  });

  // Load home page by default
  showHome();
});
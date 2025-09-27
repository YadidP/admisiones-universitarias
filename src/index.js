const express = require('express');
const cors = require('cors');
require('dotenv').config();
const { sequelize, connectDB } = require('./config/database');

const app = express();
const port = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());
app.use(express.static('public'));

app.get('/', (req, res) => {
  res.sendFile('index.html', { root: 'public' });
});

// Import routes
const userRoutes = require('./routes/user.routes');
const roleRoutes = require('./routes/role.routes');
const postulanteRoutes = require('./routes/postulante.routes');
const facultadRoutes = require('./routes/facultad.routes');
const carreraRoutes = require('./routes/carrera.routes');
const semestreRoutes = require('./routes/semestre.routes');
const cupoCarreraRoutes = require('./routes/cupocarrera.routes');
const estadoAdmisionRoutes = require('./routes/estadoadmision.routes');
const asignaturaRoutes = require('./routes/asignatura.routes');
const cursoPreuniversitarioRoutes = require('./routes/cursopreuniversitario.routes');
const aulaRoutes = require('./routes/aula.routes');
const examenRoutes = require('./routes/examen.routes');
const resultadoExamenRoutes = require('./routes/resultadoexamen.routes');
const estadoDocumentoRoutes = require('./routes/estadodocumento.routes');
const documentoRoutes = require('./routes/documento.routes');
const estadoPagoRoutes = require('./routes/estadopago.routes');
const comprobantePagoRoutes = require('./routes/comprobantepago.routes');
const estadoSolicitudRoutes = require('./routes/estadosolicitud.routes');
const solicitudCorreccionRoutes = require('./routes/solicitudcorreccion.routes');
const notificacionRoutes = require('./routes/notificacion.routes');

// Use routes
app.use('/api/users', userRoutes);
app.use('/api/roles', roleRoutes);
app.use('/api/postulantes', postulanteRoutes);
app.use('/api/facultades', facultadRoutes);
app.use('/api/carreras', carreraRoutes);
app.use('/api/semestres', semestreRoutes);
app.use('/api/cupos-carrera', cupoCarreraRoutes);
app.use('/api/estados-admision', estadoAdmisionRoutes);
app.use('/api/asignaturas', asignaturaRoutes);
app.use('/api/cursos-preuniversitarios', cursoPreuniversitarioRoutes);
app.use('/api/aulas', aulaRoutes);
app.use('/api/examenes', examenRoutes);
app.use('/api/resultados-examen', resultadoExamenRoutes);
app.use('/api/estados-documento', estadoDocumentoRoutes);
app.use('/api/documentos', documentoRoutes);
app.use('/api/estados-pago', estadoPagoRoutes);
app.use('/api/comprobantes-pago', comprobantePagoRoutes);
app.use('/api/estados-solicitud', estadoSolicitudRoutes);
app.use('/api/solicitudes-correccion', solicitudCorreccionRoutes);
app.use('/api/notificaciones', notificacionRoutes);


const startServer = async () => {
  try {
    await connectDB();
    await sequelize.sync({ force: true }); // Use { force: true } only for development
    console.log('Database synchronized');

    app.listen(port, () => {
      console.log(`Server is running on port ${port}`);
    });
  } catch (error) {
    console.error('Failed to start server:', error);
  }
};

startServer();
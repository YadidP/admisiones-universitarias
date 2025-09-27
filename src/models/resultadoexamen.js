const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Postulante = require('./postulante');
const Examen = require('./examen');

const ResultadoExamen = sequelize.define('ResultadoExamen', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  calificacion: {
    type: DataTypes.FLOAT,
    allowNull: false,
  },
}, {
  tableName: 'resultados_examen',
  timestamps: false,
});

ResultadoExamen.belongsTo(Postulante, { foreignKey: 'postulanteId' });
Postulante.hasMany(ResultadoExamen, { foreignKey: 'postulanteId' });

ResultadoExamen.belongsTo(Examen, { foreignKey: 'examenId' });
Examen.hasMany(ResultadoExamen, { foreignKey: 'examenId' });

module.exports = ResultadoExamen;
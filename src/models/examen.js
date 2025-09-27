const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Asignatura = require('./asignatura');
const Semestre = require('./semestre');
const Aula = require('./aula');

const Examen = sequelize.define('Examen', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  fecha: {
    type: DataTypes.DATEONLY,
    allowNull: false,
  },
  hora: {
    type: DataTypes.TIME,
    allowNull: false,
  },
}, {
  tableName: 'examenes',
  timestamps: false,
});

Examen.belongsTo(Asignatura, { foreignKey: 'asignaturaId' });
Asignatura.hasMany(Examen, { foreignKey: 'asignaturaId' });

Examen.belongsTo(Semestre, { foreignKey: 'semestreId' });
Semestre.hasMany(Examen, { foreignKey: 'semestreId' });

Examen.belongsTo(Aula, { foreignKey: 'aulaId' });
Aula.hasMany(Examen, { foreignKey: 'aulaId' });

module.exports = Examen;
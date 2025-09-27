const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Asignatura = require('./asignatura');
const Semestre = require('./semestre');

const CursoPreuniversitario = sequelize.define('CursoPreuniversitario', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  nombre: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  descripcion: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
  creditos: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
}, {
  tableName: 'cursos_preuniversitarios',
  timestamps: false,
});

CursoPreuniversitario.belongsTo(Asignatura, { foreignKey: 'asignaturaId' });
Asignatura.hasMany(CursoPreuniversitario, { foreignKey: 'asignaturaId' });

CursoPreuniversitario.belongsTo(Semestre, { foreignKey: 'semestreId' });
Semestre.hasMany(CursoPreuniversitario, { foreignKey: 'semestreId' });

module.exports = CursoPreuniversitario;
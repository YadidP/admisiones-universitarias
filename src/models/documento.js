const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Postulante = require('./postulante');
const EstadoDocumento = require('./estadodocumento');

const Documento = sequelize.define('Documento', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  nombre: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  url_documento: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  fecha_subida: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: DataTypes.NOW,
  },
}, {
  tableName: 'documentos',
  timestamps: false,
});

Documento.belongsTo(Postulante, { foreignKey: 'postulanteId' });
Postulante.hasMany(Documento, { foreignKey: 'postulanteId' });

Documento.belongsTo(EstadoDocumento, { foreignKey: 'estadoDocumentoId' });
EstadoDocumento.hasMany(Documento, { foreignKey: 'estadoDocumentoId' });

module.exports = Documento;
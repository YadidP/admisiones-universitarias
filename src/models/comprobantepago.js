const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Postulante = require('./postulante');
const EstadoPago = require('./estadopago');

const ComprobantePago = sequelize.define('ComprobantePago', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  url_comprobante: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  fecha_pago: {
    type: DataTypes.DATE,
    allowNull: false,
  },
  monto: {
    type: DataTypes.FLOAT,
    allowNull: false,
  },
}, {
  tableName: 'comprobantes_pago',
  timestamps: false,
});

ComprobantePago.belongsTo(Postulante, { foreignKey: 'postulanteId' });
Postulante.hasMany(ComprobantePago, { foreignKey: 'postulanteId' });

ComprobantePago.belongsTo(EstadoPago, { foreignKey: 'estadoPagoId' });
EstadoPago.hasMany(ComprobantePago, { foreignKey: 'estadoPagoId' });

module.exports = ComprobantePago;
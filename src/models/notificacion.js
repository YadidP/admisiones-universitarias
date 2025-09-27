const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const User = require('./user');

const Notificacion = sequelize.define('Notificacion', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  mensaje: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
  fecha_envio: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: DataTypes.NOW,
  },
  leida: {
    type: DataTypes.BOOLEAN,
    allowNull: false,
    defaultValue: false,
  },
}, {
  tableName: 'notificaciones',
  timestamps: false,
});

Notificacion.belongsTo(User, { foreignKey: 'userId' });
User.hasMany(Notificacion, { foreignKey: 'userId' });

module.exports = Notificacion;
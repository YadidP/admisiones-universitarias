const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const EstadoSolicitud = require('../models/estadosolicitud');

const estadoSolicitudController = new GenericController(EstadoSolicitud);

router.get('/', estadoSolicitudController.getAll);
router.post('/', estadoSolicitudController.create);
router.get('/:id', estadoSolicitudController.getById);
router.put('/:id', estadoSolicitudController.update);
router.delete('/:id', estadoSolicitudController.delete);

module.exports = router;
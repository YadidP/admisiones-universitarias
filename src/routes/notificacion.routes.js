const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Notificacion = require('../models/notificacion');

const notificacionController = new GenericController(Notificacion);

router.get('/', notificacionController.getAll);
router.post('/', notificacionController.create);
router.get('/:id', notificacionController.getById);
router.put('/:id', notificacionController.update);
router.delete('/:id', notificacionController.delete);

module.exports = router;
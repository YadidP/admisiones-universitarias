const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const EstadoDocumento = require('../models/estadodocumento');

const estadoDocumentoController = new GenericController(EstadoDocumento);

router.get('/', estadoDocumentoController.getAll);
router.post('/', estadoDocumentoController.create);
router.get('/:id', estadoDocumentoController.getById);
router.put('/:id', estadoDocumentoController.update);
router.delete('/:id', estadoDocumentoController.delete);

module.exports = router;
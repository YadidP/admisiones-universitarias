const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Documento = require('../models/documento');

const documentoController = new GenericController(Documento);

router.get('/', documentoController.getAll);
router.post('/', documentoController.create);
router.get('/:id', documentoController.getById);
router.put('/:id', documentoController.update);
router.delete('/:id', documentoController.delete);

module.exports = router;
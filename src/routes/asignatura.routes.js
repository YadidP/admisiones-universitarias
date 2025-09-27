const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Asignatura = require('../models/asignatura');

const asignaturaController = new GenericController(Asignatura);

router.get('/', asignaturaController.getAll);
router.post('/', asignaturaController.create);
router.get('/:id', asignaturaController.getById);
router.put('/:id', asignaturaController.update);
router.delete('/:id', asignaturaController.delete);

module.exports = router;
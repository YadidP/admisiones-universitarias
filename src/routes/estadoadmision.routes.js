const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const EstadoAdmision = require('../models/estadoadmision');

const estadoAdmisionController = new GenericController(EstadoAdmision);

router.get('/', estadoAdmisionController.getAll);
router.post('/', estadoAdmisionController.create);
router.get('/:id', estadoAdmisionController.getById);
router.put('/:id', estadoAdmisionController.update);
router.delete('/:id', estadoAdmisionController.delete);

module.exports = router;
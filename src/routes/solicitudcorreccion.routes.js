const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const SolicitudCorreccion = require('../models/solicitudcorreccion');

const solicitudCorreccionController = new GenericController(SolicitudCorreccion);

router.get('/', solicitudCorreccionController.getAll);
router.post('/', solicitudCorreccionController.create);
router.get('/:id', solicitudCorreccionController.getById);
router.put('/:id', solicitudCorreccionController.update);
router.delete('/:id', solicitudCorreccionController.delete);

module.exports = router;
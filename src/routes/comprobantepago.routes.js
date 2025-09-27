const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const ComprobantePago = require('../models/comprobantepago');

const comprobantePagoController = new GenericController(ComprobantePago);

router.get('/', comprobantePagoController.getAll);
router.post('/', comprobantePagoController.create);
router.get('/:id', comprobantePagoController.getById);
router.put('/:id', comprobantePagoController.update);
router.delete('/:id', comprobantePagoController.delete);

module.exports = router;
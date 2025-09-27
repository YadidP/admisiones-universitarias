const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const EstadoPago = require('../models/estadopago');

const estadoPagoController = new GenericController(EstadoPago);

router.get('/', estadoPagoController.getAll);
router.post('/', estadoPagoController.create);
router.get('/:id', estadoPagoController.getById);
router.put('/:id', estadoPagoController.update);
router.delete('/:id', estadoPagoController.delete);

module.exports = router;
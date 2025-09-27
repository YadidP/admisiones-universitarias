const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Role = require('../models/role');

const roleController = new GenericController(Role);

router.get('/', roleController.getAll);
router.post('/', roleController.create);
router.get('/:id', roleController.getById);
router.put('/:id', roleController.update);
router.delete('/:id', roleController.delete);

module.exports = router;
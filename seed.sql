-- All sample data I used to test program
-- First many students were given same pin (1234) to make testing as easy as possible

-- seed.sql
USE campus_vending;

-- Vending Machines
INSERT INTO vending_machines (machine_id, location, status) VALUES
('VM-001', 'Student Center - Main Lobby', 'ACTIVE'),
('VM-002', 'Library - 1st Floor', 'ACTIVE'),
('VM-003', 'Library - 3rd Floor', 'ACTIVE'),
('VM-004', 'Engineering Building - Lobby', 'ACTIVE'),
('VM-005', 'Engineering Building - 2nd Floor', 'ACTIVE'),
('VM-006', 'Dormitory A - Common Room', 'ACTIVE'),
('VM-007', 'Dormitory B - Lobby', 'ACTIVE'),
('VM-008', 'Gym - Main Entrance', 'ACTIVE'),
('VM-009', 'Science Building - 1st Floor', 'ACTIVE'),
('VM-010', 'Science Building - 3rd Floor', 'ACTIVE'),
('VM-011', 'Arts Building - Lobby', 'ACTIVE'),
('VM-012', 'Cafeteria - East Wing', 'ACTIVE'),
('VM-013', 'Business School - Atrium', 'ACTIVE'),
('VM-014', 'Medical Center - Waiting Area', 'ACTIVE'),
('VM-015', 'Student Union - Food Court', 'ACTIVE');

-- Products
INSERT INTO products (product_id, name, price, category) VALUES
('P0001','Chips',1.50,'Snacks'),
('P0002','Soda',1.25,'Drinks'),
('P0003','Candy Bar',1.75,'Candy'),
('P0004','Cookies',1.60,'Snacks'),
('P0006','Water',1.00,'Drinks'),
('P0007','Granola Bar',1.40,'Snacks'),
('P0008','Juice',1.80,'Drinks'),
('P0009','Iced Tea',1.70,'Drinks'),
('P0011','Gum',0.99,'Candy'),
('P0013','Pretzels',1.30,'Snacks'),
('P0014','Trail Mix',2.25,'Snacks'),
('P0015','Protein Shake',2.75,'Drinks'),
('P0016','Energy Drink',2.50,'Drinks'),
('P0017','Crackers',1.20,'Snacks'),
('P0018','Mints',0.95,'Candy'),
('P0019','Vitamin Water',2.00,'Drinks'),
('P0020','Sports Drink',2.10,'Drinks'),
('P0021','Chocolate Cookies',1.85,'Snacks'),
('P0023','Peanut Butter Crackers',1.70,'Snacks'),
('P0025','Popcorn',1.40,'Snacks'),
('P0026','Cheese Crackers',1.55,'Snacks'),
('P0027','Fruit Snacks',1.35,'Candy'),
('P0029','Rice Cakes',1.60,'Snacks'),
('P0030','Mixed Nuts',2.00,'Snacks'),
('P0031','Protein Bar',2.20,'Snacks'),
('P0032','Electrolyte Drink',2.30,'Drinks'),
('P0033','Chocolate Milk',1.90,'Drinks'),
('P0034','Vanilla Wafer',1.50,'Snacks'),
('P0035','Brownie',1.80,'Snacks'),
('P0036','Oat Bar',1.45,'Snacks'),
('P0037','Rice Krispy Treat',1.25,'Snacks'),
('P0038','Apple Chips',1.70,'Snacks'),
('P0039','Banana Chips',1.65,'Snacks'),
('P0040','Yogurt Bites',1.95,'Snacks'),
('P0041','Cola',1.50,'Drinks'),
('P0042','Orange Soda',1.50,'Drinks'),
('P0044','Root Beer',1.55,'Drinks'),
('P0046','Lemon Soda',1.45,'Drinks'),
('P0047','Diet Cola',1.50,'Drinks'),
('P0049','Sparkling Water',1.60,'Drinks');

-- Inventory: Stock all 15 vending machines with products
-- VM-001
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-001', 'P0001', 'A1', 20), ('VM-001', 'P0002', 'A2', 18), ('VM-001', 'P0003', 'A3', 15),
('VM-001', 'P0006', 'A4', 25), ('VM-001', 'P0008', 'A5', 12), ('VM-001', 'P0013', 'B1', 10),
('VM-001', 'P0021', 'B2', 15), ('VM-001', 'P0023', 'B3', 18), ('VM-001', 'P0025', 'B4', 12),
('VM-001', 'P0030', 'B5', 10), ('VM-001', 'P0033', 'C1', 14), ('VM-001', 'P0034', 'C2', 8),
('VM-001', 'P0041', 'C3', 20), ('VM-001', 'P0042', 'C4', 22), ('VM-001', 'P0044', 'C5', 18),
('VM-001', 'P0046', 'D1', 16), ('VM-001', 'P0047', 'D2', 20), ('VM-001', 'P0011', 'D3', 10),
('VM-001', 'P0039', 'D4', 12), ('VM-001', 'P0018', 'D5', 8);

-- VM-002
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-002', 'P0006', 'A1', 20), ('VM-002', 'P0017', 'A2', 15), ('VM-002', 'P0001', 'A3', 12),
('VM-002', 'P0003', 'A4', 10), ('VM-002', 'P0011', 'A5', 8), ('VM-002', 'P0033', 'B1', 18),
('VM-002', 'P0034', 'B2', 12), ('VM-002', 'P0030', 'B3', 10), ('VM-002', 'P0027', 'B4', 15),
('VM-002', 'P0029', 'B5', 12), ('VM-002', 'P0041', 'C1', 14), ('VM-002', 'P0046', 'C2', 16),
('VM-002', 'P0047', 'C3', 18), ('VM-002', 'P0021', 'C4', 10), ('VM-002', 'P0023', 'C5', 8),
('VM-002', 'P0042', 'D1', 15), ('VM-002', 'P0044', 'D2', 12), ('VM-002', 'P0009', 'D3', 6),
('VM-002', 'P0039', 'D4', 10), ('VM-002', 'P0038', 'D5', 20);

-- VM-003
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-003', 'P0006', 'A1', 18), ('VM-003', 'P0017', 'A2', 12), ('VM-003', 'P0013', 'A3', 8),
('VM-003', 'P0001', 'A4', 10), ('VM-003', 'P0002', 'A5', 10), ('VM-003', 'P0034', 'B1', 15),
('VM-003', 'P0035', 'B2', 10), ('VM-003', 'P0033', 'B3', 12), ('VM-003', 'P0030', 'B4', 8),
('VM-003', 'P0027', 'B5', 14), ('VM-003', 'P0041', 'C1', 16), ('VM-003', 'P0042', 'C2', 18),
('VM-003', 'P0046', 'C3', 14), ('VM-003', 'P0047', 'C4', 16), ('VM-003', 'P0049', 'C5', 20),
('VM-003', 'P0021', 'D1', 8), ('VM-003', 'P0023', 'D2', 6), ('VM-003', 'P0039', 'D3', 12),
('VM-003', 'P0040', 'D4', 10), ('VM-003', 'P0011', 'D5', 8);

-- VM-004
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-004', 'P0013', 'A1', 15), ('VM-004', 'P0014', 'A2', 12), ('VM-004', 'P0001', 'A3', 18),
('VM-004', 'P0004', 'A4', 20), ('VM-004', 'P0006', 'A5', 22), ('VM-004', 'P0034', 'B1', 10),
('VM-004', 'P0035', 'B2', 8), ('VM-004', 'P0037', 'B3', 6), ('VM-004', 'P0021', 'B4', 15),
('VM-004', 'P0023', 'B5', 18), ('VM-004', 'P0025', 'C1', 12), ('VM-004', 'P0030', 'C2', 10),
('VM-004', 'P0041', 'C3', 20), ('VM-004', 'P0042', 'C4', 18), ('VM-004', 'P0046', 'C5', 16),
('VM-004', 'P0047', 'D1', 22), ('VM-004', 'P0039', 'D2', 8), ('VM-004', 'P0033', 'D3', 12),
('VM-004', 'P0017', 'D4', 10), ('VM-004', 'P0011', 'D5', 8);

-- VM-005
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-005', 'P0006', 'A1', 15), ('VM-005', 'P0001', 'A2', 12), ('VM-005', 'P0003', 'A3', 10),
('VM-005', 'P0013', 'A4', 8), ('VM-005', 'P0017', 'A5', 10), ('VM-005', 'P0034', 'B1', 12),
('VM-005', 'P0033', 'B2', 15), ('VM-005', 'P0030', 'B3', 8), ('VM-005', 'P0021', 'B4', 10),
('VM-005', 'P0023', 'B5', 12), ('VM-005', 'P0041', 'C1', 18), ('VM-005', 'P0042', 'C2', 16),
('VM-005', 'P0046', 'C3', 14), ('VM-005', 'P0047', 'C4', 20), ('VM-005', 'P0025', 'C5', 10),
('VM-005', 'P0027', 'D1', 12), ('VM-005', 'P0039', 'D2', 8), ('VM-005', 'P0011', 'D3', 6),
('VM-005', 'P0044', 'D4', 15), ('VM-005', 'P0049', 'D5', 18);

-- VM-006
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-006', 'P0001', 'A1', 20), ('VM-006', 'P0002', 'A2', 18), ('VM-006', 'P0004', 'A3', 15),
('VM-006', 'P0006', 'A4', 25), ('VM-006', 'P0008', 'A5', 10), ('VM-006', 'P0021', 'B1', 20),
('VM-006', 'P0023', 'B2', 22), ('VM-006', 'P0025', 'B3', 18), ('VM-006', 'P0026', 'B4', 15),
('VM-006', 'P0039', 'B5', 12), ('VM-006', 'P0040', 'C1', 12), ('VM-006', 'P0041', 'C2', 25),
('VM-006', 'P0042', 'C3', 28), ('VM-006', 'P0044', 'C4', 22), ('VM-006', 'P0046', 'C5', 20),
('VM-006', 'P0047', 'D1', 25), ('VM-006', 'P0049', 'D2', 24), ('VM-006', 'P0033', 'D3', 10),
('VM-006', 'P0038', 'D4', 18), ('VM-006', 'P0030', 'D5', 8);

-- VM-007
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-007', 'P0001', 'A1', 18), ('VM-007', 'P0002', 'A2', 16), ('VM-007', 'P0003', 'A3', 14),
('VM-007', 'P0006', 'A4', 22), ('VM-007', 'P0013', 'A5', 10), ('VM-007', 'P0021', 'B1', 18),
('VM-007', 'P0023', 'B2', 20), ('VM-007', 'P0025', 'B3', 16), ('VM-007', 'P0039', 'B4', 10),
('VM-007', 'P0040', 'B5', 10), ('VM-007', 'P0041', 'C1', 22), ('VM-007', 'P0042', 'C2', 24),
('VM-007', 'P0044', 'C3', 20), ('VM-007', 'P0046', 'C4', 18), ('VM-007', 'P0047', 'C5', 22),
('VM-007', 'P0049', 'D1', 20), ('VM-007', 'P0033', 'D2', 12), ('VM-007', 'P0034', 'D3', 8),
('VM-007', 'P0030', 'D4', 6), ('VM-007', 'P0011', 'D5', 8);

-- VM-008
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-008', 'P0006', 'A1', 30), ('VM-008', 'P0015', 'A2', 25), ('VM-008', 'P0016', 'A3', 22),
('VM-008', 'P0014', 'A4', 15), ('VM-008', 'P0019', 'A5', 12), ('VM-008', 'P0034', 'B1', 20),
('VM-008', 'P0035', 'B2', 18), ('VM-008', 'P0033', 'B3', 15), ('VM-008', 'P0030', 'B4', 12),
('VM-008', 'P0037', 'B5', 8), ('VM-008', 'P0031', 'C1', 10), ('VM-008', 'P0032', 'C2', 8),
('VM-008', 'P0021', 'C3', 10), ('VM-008', 'P0027', 'C4', 15), ('VM-008', 'P0038', 'C5', 12),
('VM-008', 'P0041', 'D1', 15), ('VM-008', 'P0042', 'D2', 15), ('VM-008', 'P0009', 'D3', 8),
('VM-008', 'P0020', 'D4', 10), ('VM-008', 'P0008', 'D5', 8);

-- VM-009
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-009', 'P0001', 'A1', 15), ('VM-009', 'P0006', 'A2', 18), ('VM-009', 'P0017', 'A3', 12),
('VM-009', 'P0011', 'A4', 10), ('VM-009', 'P0013', 'A5', 8), ('VM-009', 'P0033', 'B1', 15),
('VM-009', 'P0034', 'B2', 10), ('VM-009', 'P0030', 'B3', 8), ('VM-009', 'P0021', 'B4', 12),
('VM-009', 'P0023', 'B5', 14), ('VM-009', 'P0041', 'C1', 18), ('VM-009', 'P0042', 'C2', 20),
('VM-009', 'P0046', 'C3', 16), ('VM-009', 'P0047', 'C4', 18), ('VM-009', 'P0049', 'C5', 22),
('VM-009', 'P0039', 'D1', 10), ('VM-009', 'P0040', 'D2', 12), ('VM-009', 'P0038', 'D3', 8),
('VM-009', 'P0037', 'D4', 6), ('VM-009', 'P0035', 'D5', 10);

-- VM-010
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-010', 'P0001', 'A1', 20), ('VM-010', 'P0003', 'A2', 18), ('VM-010', 'P0004', 'A3', 15),
('VM-010', 'P0006', 'A4', 25), ('VM-010', 'P0008', 'A5', 12), ('VM-010', 'P0021', 'B1', 10),
('VM-010', 'P0023', 'B2', 15), ('VM-010', 'P0025', 'B3', 18), ('VM-010', 'P0030', 'B4', 12),
('VM-010', 'P0033', 'B5', 10), ('VM-010', 'P0041', 'C1', 14), ('VM-010', 'P0042', 'C2', 16),
('VM-010', 'P0044', 'C3', 18), ('VM-010', 'P0046', 'C4', 20), ('VM-010', 'P0047', 'C5', 22),
('VM-010', 'P0049', 'D1', 18), ('VM-010', 'P0039', 'D2', 12), ('VM-010', 'P0038', 'D3', 10),
('VM-010', 'P0037', 'D4', 8), ('VM-010', 'P0035', 'D5', 12);

-- VM-011
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-011', 'P0001', 'A1', 12), ('VM-011', 'P0002', 'A2', 15), ('VM-011', 'P0003', 'A3', 10),
('VM-011', 'P0004', 'A4', 12), ('VM-011', 'P0006', 'A5', 20), ('VM-011', 'P0021', 'B1', 10),
('VM-011', 'P0023', 'B2', 12), ('VM-011', 'P0025', 'B3', 15), ('VM-011', 'P0030', 'B4', 10),
('VM-011', 'P0033', 'B5', 8), ('VM-011', 'P0041', 'C1', 12), ('VM-011', 'P0042', 'C2', 14),
('VM-011', 'P0044', 'C3', 10), ('VM-011', 'P0046', 'C4', 12), ('VM-011', 'P0047', 'C5', 16),
('VM-011', 'P0049', 'D1', 18), ('VM-011', 'P0039', 'D2', 10), ('VM-011', 'P0038', 'D3', 8),
('VM-011', 'P0037', 'D4', 6), ('VM-011', 'P0035', 'D5', 10);

-- VM-012
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-012', 'P0001', 'A1', 25), ('VM-012', 'P0002', 'A2', 20), ('VM-012', 'P0003', 'A3', 18),
('VM-012', 'P0004', 'A4', 15), ('VM-012', 'P0006', 'A5', 30), ('VM-012', 'P0021', 'B1', 20),
('VM-012', 'P0023', 'B2', 18), ('VM-012', 'P0025', 'B3', 15), ('VM-012', 'P0030', 'B4', 12),
('VM-012', 'P0033', 'B5', 10), ('VM-012', 'P0041', 'C1', 18), ('VM-012', 'P0042', 'C2', 20),
('VM-012', 'P0044', 'C3', 22), ('VM-012', 'P0046', 'C4', 24), ('VM-012', 'P0047', 'C5', 26),
('VM-012', 'P0049', 'D1', 28), ('VM-012', 'P0039', 'D2', 12), ('VM-012', 'P0038', 'D3', 10),
('VM-012', 'P0037', 'D4', 8), ('VM-012', 'P0035', 'D5', 12);

-- VM-013
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-013', 'P0001', 'A1', 10), ('VM-013', 'P0002', 'A2', 12), ('VM-013', 'P0003', 'A3', 8),
('VM-013', 'P0004', 'A4', 10), ('VM-013', 'P0006', 'A5', 15), ('VM-013', 'P0021', 'B1', 10),
('VM-013', 'P0023', 'B2', 12), ('VM-013', 'P0025', 'B3', 8), ('VM-013', 'P0030', 'B4', 10),
('VM-013', 'P0033', 'B5', 12), ('VM-013', 'P0041', 'C1', 14), ('VM-013', 'P0042', 'C2', 16),
('VM-013', 'P0044', 'C3', 18), ('VM-013', 'P0046', 'C4', 20), ('VM-013', 'P0047', 'C5', 22),
('VM-013', 'P0049', 'D1', 24), ('VM-013', 'P0039', 'D2', 12), ('VM-013', 'P0038', 'D3', 10),
('VM-013', 'P0037', 'D4', 8), ('VM-013', 'P0035', 'D5', 12);

-- VM-014
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-014', 'P0006', 'A1', 20), ('VM-014', 'P0015', 'A2', 15), ('VM-014', 'P0016', 'A3', 12),
('VM-014', 'P0019', 'A4', 10), ('VM-014', 'P0020', 'A5', 8), ('VM-014', 'P0033', 'B1', 14),
('VM-014', 'P0034', 'B2', 12), ('VM-014', 'P0030', 'B3', 10), ('VM-014', 'P0031', 'B4', 8),
('VM-014', 'P0032', 'B5', 6), ('VM-014', 'P0041', 'C1', 18), ('VM-014', 'P0042', 'C2', 16),
('VM-014', 'P0044', 'C3', 14), ('VM-014', 'P0046', 'C4', 12), ('VM-014', 'P0047', 'C5', 10),
('VM-014', 'P0049', 'D1', 8), ('VM-014', 'P0035', 'D2', 6), ('VM-014', 'P0038', 'D3', 10),
('VM-014', 'P0039', 'D4', 12), ('VM-014', 'P0040', 'D5', 14);

-- VM-015
INSERT INTO inventory (machine_id, product_id, slot_number, quantity) VALUES
('VM-015', 'P0001', 'A1', 20), ('VM-015', 'P0002', 'A2', 18), ('VM-015', 'P0003', 'A3', 15),
('VM-015', 'P0004', 'A4', 12), ('VM-015', 'P0006', 'A5', 25), ('VM-015', 'P0021', 'B1', 10),
('VM-015', 'P0023', 'B2', 12), ('VM-015', 'P0025', 'B3', 15), ('VM-015', 'P0030', 'B4', 12),
('VM-015', 'P0033', 'B5', 14), ('VM-015', 'P0041', 'C1', 18), ('VM-015', 'P0042', 'C2', 16),
('VM-015', 'P0044', 'C3', 14), ('VM-015', 'P0046', 'C4', 20), ('VM-015', 'P0047', 'C5', 22),
('VM-015', 'P0049', 'D1', 20), ('VM-015', 'P0039', 'D2', 12), ('VM-015', 'P0038', 'D3', 10),
('VM-015', 'P0037', 'D4', 8), ('VM-015', 'P0035', 'D5', 12);

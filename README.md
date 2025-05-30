# 🥪 DELI-cious POS

A Java-based Point-of-Sale system for sandwich shops — designed for quick ordering, flexible customization, receipt generation, and CSV exporting.

---

## 🚀 Features

- 🔧 Custom sandwich builder (bread, size, toasted, toppings)
- ⭐ Signature sandwich presets (BLT, Philly)
- 🥤 Add drinks with sizes & flavors
- 🍟 Add chips with flavor
- 💵 Checkout with payment method selection
- 🧾 Save receipts to `/receipts`
- 📂 View past orders anytime
- 📊 Export orders to `orders.csv` for analysis

---

## 📁 Folder Structure

DELI-cious-POS/
├── src/
│ ├── models/
│ │ ├── Sandwich.java, Drink.java, Chip.java, etc.
│ ├── enums/
│ │ ├── BreadType.java, ToppingType.java, etc.
│ └── services/
│ └── OrderManager.java
├── receipts/
│ └── [auto-saved receipts]
├── orders.csv
├── README.md



---

## 🧑‍💻 How to Run

### 🔨 Compile (from project root):
```bash
javac -d out src/models/enums/*.java src/models/*.java src/services/*.java

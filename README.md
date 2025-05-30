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



Flow chart -using PlantUML
![image](https://github.com/user-attachments/assets/1a8272d7-7520-4384-8f75-d978789841a1)


---

## 🧑‍💻 How to Run

### 🔨 Compile (from project root):
```bash
javac -d out src/models/enums/*.java src/models/*.java src/services/*.java

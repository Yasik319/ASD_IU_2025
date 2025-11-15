import java.util.Stack;

/**
 * Стек с минимумом - поддерживает операции push, pop, top, getMin за O(1)
 * Логика: используем два стека - один для хранения элементов,
 * другой для хранения текущего минимума
 */
class MinStack {
    private Stack<Integer> stack;    // Основной стек
    private Stack<Integer> minStack; // Стек для хранения минимумов

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Добавление элемента в стек
     * Если новый элемент меньше или равен текущему минимуму,
     * добавляем его также в minStack
     */
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    /**
     * Удаление верхнего элемента
     * Если удаляемый элемент равен текущему минимуму,
     * удаляем его также из minStack
     */
    public void pop() {
        if (stack.isEmpty()) return;

        int popped = stack.pop();
        if (popped == minStack.peek()) {
            minStack.pop();
        }
    }

    /**
     * Получение верхнего элемента без удаления
     */
    public int top() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.peek();
    }

    /**
     * Получение текущего минимального элемента за O(1)
     */
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return minStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    /**
     * Печать текущего состояния стеков (для демонстрации)
     */
    public void printStacks() {
        System.out.println("Основной стек: " + stack);
        System.out.println("Стек минимумов: " + minStack);
        if (!isEmpty()) {
            System.out.println("Верхний элемент: " + top() + ", Текущий минимум: " + getMin());
        } else {
            System.out.println("Стек пуст");
        }
        System.out.println();
    }
}

/**
 * Демонстрационный класс для тестирования MinStack
 */
class MinStackDemo {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ MIN STACK ===\n");

        // Создаем стек с минимумом
        MinStack minStack = new MinStack();
        System.out.println("1. Создан пустой MinStack");
        minStack.printStacks();

        // Тест 1: Добавление элементов в возрастающем порядке
        System.out.println("2. Добавляем элементы в ВОЗРАСТАЮЩЕМ порядке: 5, 10, 15");
        minStack.push(5);
        minStack.printStacks();

        minStack.push(10);
        minStack.printStacks();

        minStack.push(15);
        minStack.printStacks();

        // Тест 2: Добавление элементов в убывающем порядке
        System.out.println("3. Добавляем элементы в УБЫВАЮЩЕМ порядке: 3, 2, 1");
        minStack.push(3);
        minStack.printStacks();

        minStack.push(2);
        minStack.printStacks();

        minStack.push(1);
        minStack.printStacks();

        // Тест 3: Добавление элементов со случайными значениями
        System.out.println("4. Добавляем элементы со СЛУЧАЙНЫМИ значениями: 4, 0, 7");
        minStack.push(4);
        minStack.printStacks();

        minStack.push(0);
        minStack.printStacks();

        minStack.push(7);
        minStack.printStacks();

        // Тест 4: Удаление элементов
        System.out.println("5. Удаляем элементы по одному и отслеживаем минимум:");

        System.out.println("Удаляем верхний элемент (7):");
        minStack.pop();
        minStack.printStacks();

        System.out.println("Удаляем верхний элемент (0) - это текущий минимум:");
        minStack.pop();
        minStack.printStacks();

        System.out.println("Удаляем верхний элемент (4):");
        minStack.pop();
        minStack.printStacks();

        System.out.println("Удаляем верхний элемент (1) - это текущий минимум:");
        minStack.pop();
        minStack.printStacks();

        // Тест 5: Демонстрация работы с дубликатами минимумов
        System.out.println("6. Демонстрация работы с ДУБЛИКАТАМИ минимумов:");

        MinStack duplicateStack = new MinStack();
        System.out.println("Добавляем 5, 3, 3, 8, 2, 2:");
        duplicateStack.push(5);
        duplicateStack.printStacks();

        duplicateStack.push(3);
        duplicateStack.printStacks();

        duplicateStack.push(3); // Дубликат минимума
        duplicateStack.printStacks();

        duplicateStack.push(8);
        duplicateStack.printStacks();

        duplicateStack.push(2); // Новый минимум
        duplicateStack.printStacks();

        duplicateStack.push(2); // Дубликат минимума
        duplicateStack.printStacks();

        System.out.println("Удаляем элементы и отслеживаем минимум:");
        duplicateStack.pop(); // Удаляем 2 (дубликат)
        duplicateStack.printStacks();

        duplicateStack.pop(); // Удаляем 2 (минимум)
        duplicateStack.printStacks();

        duplicateStack.pop(); // Удаляем 8
        duplicateStack.printStacks();

        duplicateStack.pop(); // Удаляем 3 (дубликат)
        duplicateStack.printStacks();

        duplicateStack.pop(); // Удаляем 3 (минимум)
        duplicateStack.printStacks();

        // Тест 6: Обработка ошибок
        System.out.println("7. Тестирование обработки ошибок:");

        MinStack emptyStack = new MinStack();
        try {
            System.out.println("Попытка получить верхний элемент пустого стека:");
            emptyStack.top();
        } catch (IllegalStateException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        try {
            System.out.println("Попытка получить минимум пустого стека:");
            emptyStack.getMin();
        } catch (IllegalStateException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        // Тест 7: Комплексный сценарий
        System.out.println("8. КОМПЛЕКСНЫЙ СЦЕНАРИЙ:");

        MinStack complexStack = new MinStack();
        System.out.println("Последовательность операций:");

        System.out.println("push(8)");
        complexStack.push(8);
        complexStack.printStacks();

        System.out.println("push(4)");
        complexStack.push(4);
        complexStack.printStacks();

        System.out.println("push(6)");
        complexStack.push(6);
        complexStack.printStacks();

        System.out.println("push(2)");
        complexStack.push(2);
        complexStack.printStacks();

        System.out.println("push(5)");
        complexStack.push(5);
        complexStack.printStacks();

        System.out.println("pop() - удаляем 5");
        complexStack.pop();
        complexStack.printStacks();

        System.out.println("pop() - удаляем 2 (минимум)");
        complexStack.pop();
        complexStack.printStacks();

        System.out.println("push(1) - новый минимум");
        complexStack.push(1);
        complexStack.printStacks();

        System.out.println("pop() - удаляем 1 (минимум)");
        complexStack.pop();
        complexStack.printStacks();

        System.out.println("pop() - удаляем 6");
        complexStack.pop();
        complexStack.printStacks();

        // Тест 8: Производительность
        System.out.println("9. ТЕСТИРОВАНИЕ ПРОИЗВОДИТЕЛЬНОСТИ:");
        testPerformance();

        System.out.println("=== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ===");
    }

    /**
     * Тестирование производительности MinStack
     */
    private static void testPerformance() {
        final int OPERATIONS = 1000000;
        MinStack perfStack = new MinStack();

        long startTime, endTime;

        // Тест добавления
        startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            perfStack.push(i % 1000); // Добавляем числа от 0 до 999 циклически
        }
        endTime = System.nanoTime();
        System.out.println("Добавление " + OPERATIONS + " элементов: " +
                (endTime - startTime) / 1000000 + " ms");

        // Тест получения минимума
        startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            perfStack.getMin();
        }
        endTime = System.nanoTime();
        System.out.println("Получение минимума " + OPERATIONS + " раз: " +
                (endTime - startTime) / 1000000 + " ms");

        // Тест удаления
        startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            perfStack.pop();
        }
        endTime = System.nanoTime();
        System.out.println("Удаление " + OPERATIONS + " элементов: " +
                (endTime - startTime) / 1000000 + " ms");

        // Проверка что стек пуст
        System.out.println("Стек пуст после теста: " + perfStack.isEmpty());
    }
}
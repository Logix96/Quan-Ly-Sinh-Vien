import controller.MainController;
import repository.SinhVienRepository;
import service.SinhVienService;
import view.MainView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SinhVienRepository repository = new SinhVienRepository();
            SinhVienService service = new SinhVienService(repository);
            MainView view = new MainView();

            new MainController(view, service);

            view.setVisible(true);
        });
    }
}
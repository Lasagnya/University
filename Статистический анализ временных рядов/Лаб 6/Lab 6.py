import statsmodels.api as sm
from statsmodels.graphics import tsaplots
import matplotlib.pyplot as plt

data = [6.9, 6.7, 6.6, 6.9, 7.2, 6.8, 7.1, 7.3, 7.4, 7.8, 7.0, 6.7, 7.4, 7.0, 7.0, 6.4, 6.0, 6.3, 5.6, 7.0, 6.4, 6.7, 6.7, 6.3, 5.7, 6.1, 6.1, 5.7, 6.1, 7.4, 5.9, 6.5, 6.1, 6.2, 6.3, 6.1, 5.9]
r = sm.tsa.acf(data, nlags = 10)
print(r)
fig = tsaplots.plot_acf(data, lags=10)
plt.show()
import sys
from selenium import webdriver
from selenium.webdriver.common.by import By
import urllib
import time

# Install the chrome web driver from selenium.
# !apt-get update
# !apt install chromium-chromedriver

chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('--headless')
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--disable-dev-shm-usage')
driver = webdriver.Chrome('chromedriver',chrome_options=chrome_options)

# Create url variable containing the webpage for a Google image search.
url = ("https://www.google.com/search?q={s}&tbm=isch&tbs=sur%3Afc&hl=en&ved=0CAIQpwVqFwoTCKCa1c6s4-oCFQAAAAAdAAAAABAC&biw=1251&bih=568")

# Launch the browser and open the given url in the webdriver.
product_name = sys.argv[1]
driver.get(url.format(s=product_name))

# Scroll down the body of the web page and load the images.
driver.execute_script("window.scrollTo(0,document.body.scrollHeight);")
time.sleep(5)

# Find the images.
imgResults = driver.find_elements(By.XPATH,"//img[contains(@class,'Q4LuWd')]")

# Access and store the src list of image urls.
src = []
for img in imgResults:
    src.append(img.get_attribute('src'))

# Retrieve and download the images.
try:
    for i in range(1):
        urllib.request.urlretrieve(src[i],"src/main/sample_data/{}.jpg".format(product_name))
except Exception as e:
    print("Error downloading image:", e)
    exit(1)

exit(0)
from setuptools import setup, find_packages

setup(
    name="DataStructureAlgo",
    version="0.1.0",
    description="Data Structures and Algorithms implementations",
    author="Nitin Gupta",
    package_dir={"": "src/main/python"},
    packages=find_packages(where="src/main/python"),
    python_requires=">=3.6",
    install_requires=[],
    # Optional: Add entry points if you have command-line scripts
    # entry_points={
    #     'console_scripts': [
    #         'dsa=DataStructureAlgo.cli:main',
    #     ],
    # },
)